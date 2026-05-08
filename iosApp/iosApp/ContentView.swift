import SwiftUI
import shared

struct ContentView: View {
    var body: some View {
        NavigationStack {
            SplashView()
        }
    }
}

struct SplashView: View {
    @State private var isActive = false

    var body: some View {
        VStack {
            if isActive {
                LoginView()
            } else {
                ProgressView()
                    .scaleEffect(2)
                    .onAppear {
                        DispatchQueue.main.asyncAfter(deadline: .now() + 1) {
                            withAnimation {
                                isActive = true
                            }
                        }
                    }
            }
        }
    }
}

struct LoginView: View {
    @State private var email = ""
    @State private var password = ""
    @State private var isLoading = false
    @State private var errorMessage: String?
    @State private var isLoggedIn = false

    var body: some View {
        VStack(spacing: 24) {
            Spacer()

            Text("BeerTracker")
                .font(.largeTitle)
                .fontWeight(.bold)
                .foregroundColor(.orange)

            Spacer().frame(height: 32)

            TextField("Email", text: $email)
                .textFieldStyle(.roundedBorder)
                .keyboardType(.emailAddress)
                .autocapitalization(.none)

            SecureField("Senha", text: $password)
                .textFieldStyle(.roundedBorder)

            if let error = errorMessage {
                Text(error)
                    .foregroundColor(.red)
                    .font(.caption)
            }

            Button(action: {
                isLoading = true
                errorMessage = nil
                let repo = AuthRepository()
                repo.login(username: email, password: password) { result, error in
                    isLoading = false
                    if let error = error {
                        errorMessage = error.localizedDescription
                    } else {
                        isLoggedIn = true
                    }
                }
            }) {
                if isLoading {
                    ProgressView()
                } else {
                    Text("Login")
                        .frame(maxWidth: .infinity)
                }
            }
            .buttonStyle(.borderedProminent)
            .disabled(isLoading || email.isEmpty || password.isEmpty)

            NavigationLink(destination: RegisterView()) {
                Text("Não tem conta? Cadastre-se")
            }

            Spacer()
        }
        .padding(24)
        .navigationDestination(isPresented: $isLoggedIn) {
            HomeView()
        }
    }
}

struct RegisterView: View {
    @State private var name = ""
    @State private var email = ""
    @State private var password = ""
    @State private var ageConfirmed = false
    @State private var isLoading = false
    @State private var errorMessage: String?
    @State private var isRegistered = false
    @Environment(\.dismiss) private var dismiss

    var body: some View {
        VStack(spacing: 16) {
            Spacer()

            Text("Cadastro")
                .font(.largeTitle)
                .fontWeight(.bold)
                .foregroundColor(.orange)

            Spacer().frame(height: 24)

            TextField("Nome", text: $name)
                .textFieldStyle(.roundedBorder)

            TextField("Email", text: $email)
                .textFieldStyle(.roundedBorder)
                .keyboardType(.emailAddress)
                .autocapitalization(.none)

            SecureField("Senha", text: $password)
                .textFieldStyle(.roundedBorder)

            Toggle(isOn: $ageConfirmed) {
                Text("Confirmo que possuo 18 anos ou mais")
                    .font(.caption)
            }

            if let error = errorMessage {
                Text(error)
                    .foregroundColor(.red)
                    .font(.caption)
            }

            Button(action: {
                isLoading = true
                errorMessage = nil
                let repo = AuthRepository()
                repo.register(username: name, password: password, email: email) { result, error in
                    isLoading = false
                    if let error = error {
                        errorMessage = error.localizedDescription
                    } else {
                        isRegistered = true
                    }
                }
            }) {
                if isLoading {
                    ProgressView()
                } else {
                    Text("Cadastrar")
                        .frame(maxWidth: .infinity)
                }
            }
            .buttonStyle(.borderedProminent)
            .disabled(isLoading || name.isEmpty || email.isEmpty || password.isEmpty || !ageConfirmed)

            Button("Cancelar", action: { dismiss() })
                .buttonStyle(.bordered)

            Spacer()
        }
        .padding(24)
        .navigationDestination(isPresented: $isRegistered) {
            LoginView()
        }
    }
}

struct HomeView: View {
    var body: some View {
        VStack(spacing: 24) {
            Spacer()

            NavigationLink(destination: StyleListView()) {
                Label("Escolher Estilo", systemImage: "wineglass")
                    .frame(maxWidth: .infinity)
                    .padding()
                    .background(Color.orange)
                    .foregroundColor(.white)
                    .cornerRadius(12)
            }

            NavigationLink(destination: DriverView()) {
                Label("Motorista da Vez", systemImage: "person")
                    .frame(maxWidth: .infinity)
                    .padding()
                    .background(Color.orange)
                    .foregroundColor(.white)
                    .cornerRadius(12)
            }

            Spacer()
        }
        .padding(24)
        .navigationTitle("BeerTracker")
    }
}

struct StyleListView: View {
    @State private var styles: [Style] = []
    @State private var isLoading = true

    var body: some View {
        Group {
            if isLoading {
                ProgressView()
            } else {
                ScrollView {
                    LazyVGrid(columns: [GridItem(.flexible()), GridItem(.flexible())], spacing: 12) {
                        ForEach(styles, id: \.codEstilo) { style in
                            NavigationLink(destination: StyleDetailView(style: style)) {
                                StyleCard(style: style)
                            }
                            .buttonStyle(.plain)
                        }
                    }
                    .padding(12)
                }
            }
        }
        .navigationTitle("Estilos")
        .onAppear {
            let repo = StyleRepository()
            repo.getAllStyles { result, error in
                isLoading = false
                if let styles = result as? [Style] {
                    self.styles = styles
                }
            }
        }
    }
}

struct StyleCard: View {
    let style: Style

    var body: some View {
        VStack {
            AsyncImage(url: URL(string: style.image)) { phase in
                switch phase {
                case .success(let image):
                    image.resizable().scaledToFill()
                case .failure(_):
                    Color.gray
                case .empty:
                    ProgressView()
                @unknown default:
                    Color.gray
                }
            }
            .frame(height: 120)
            .clipped()

            Text(style.nomeEstilo)
                .font(.subheadline)
                .fontWeight(.semibold)
                .padding(8)
        }
        .background(Color(.systemBackground))
        .cornerRadius(12)
        .shadow(radius: 4)
    }
}

struct StyleDetailView: View {
    let style: Style
    @State private var harmonies: [Harmony] = []
    @State private var isLoadingHarmonies = true
    @State private var selectedTab = 0

    var body: some View {
        VStack {
            Picker("", selection: $selectedTab) {
                Text("Detalhes").tag(0)
                Text("Harmonias").tag(1)
            }
            .pickerStyle(.segmented)
            .padding()

            if selectedTab == 0 {
                detailTab
            } else {
                harmonyTab
            }
        }
        .navigationTitle(style.nomeEstilo)
        .onAppear {
            let repo = HarmonyRepository()
            repo.getHarmonies(style: style) { result, error in
                isLoadingHarmonies = false
                if let harmonies = result as? [Harmony] {
                    self.harmonies = harmonies
                }
            }
        }
    }

    var detailTab: some View {
        ScrollView {
            VStack(alignment: .leading, spacing: 16) {
                AsyncImage(url: URL(string: style.image)) { phase in
                    switch phase {
                    case .success(let image):
                        image.resizable().scaledToFit()
                    case .failure(_):
                        Color.gray.frame(height: 200)
                    case .empty:
                        ProgressView()
                    @unknown default:
                        Color.gray
                    }
                }
                .frame(maxWidth: .infinity)
                .frame(height: 200)
                .clipped()

                Text(style.nomeEstilo)
                    .font(.title)
                    .fontWeight(.bold)

                Text(style.descricao)
                    .font(.body)
            }
            .padding()
        }
    }

    var harmonyTab: some View {
        Group {
            if isLoadingHarmonies {
                ProgressView()
            } else if harmonies.isEmpty {
                Text("Nenhuma harmonia encontrada")
            } else {
                List(harmonies, id: \.codHarmonia) { harmony in
                    HStack(spacing: 12) {
                        AsyncImage(url: URL(string: harmony.imagem)) { phase in
                            switch phase {
                            case .success(let image):
                                image.resizable().scaledToFill()
                            case .failure(_):
                                Color.gray
                            case .empty:
                                ProgressView()
                            @unknown default:
                                Color.gray
                            }
                        }
                        .frame(width: 60, height: 60)
                        .clipped()
                        .cornerRadius(8)

                        VStack(alignment: .leading) {
                            Text(harmony.nomeHarmonia)
                                .font(.headline)
                            Text(harmony.descricao)
                                .font(.caption)
                                .foregroundColor(.secondary)
                        }
                    }
                }
            }
        }
    }
}

struct DriverView: View {
    @State private var drivers: [String] = []
    @State private var inputText = ""
    @State private var pickedDriver: String?
    @State private var navigateToChosen = false

    var body: some View {
        VStack(spacing: 16) {
            HStack {
                TextField("Nome do motorista", text: $inputText)
                    .textFieldStyle(.roundedBorder)

                Button(action: {
                    if !inputText.trimmingCharacters(in: .whitespaces).isEmpty {
                        drivers.append(inputText.trimmingCharacters(in: .whitespaces))
                        inputText = ""
                    }
                }) {
                    Image(systemName: "plus.circle.fill")
                        .font(.title2)
                }
            }
            .padding(.horizontal)

            List {
                ForEach(Array(drivers.enumerated()), id: \.offset) { index, driver in
                    HStack {
                        Text(driver)
                        Spacer()
                        Button(action: { drivers.remove(at: index) }) {
                            Image(systemName: "trash")
                                .foregroundColor(.red)
                        }
                    }
                }
            }

            Button(action: {
                if !drivers.isEmpty {
                    pickedDriver = drivers.randomElement()
                    navigateToChosen = true
                }
            }) {
                Text("Sortear Motorista")
                    .frame(maxWidth: .infinity)
                    .padding()
            }
            .buttonStyle(.borderedProminent)
            .disabled(drivers.isEmpty)
            .padding(.horizontal)
        }
        .navigationTitle("Motorista da Vez")
        .navigationDestination(isPresented: $navigateToChosen) {
            if let driver = pickedDriver {
                ChosenDriverView(driverName: driver)
            }
        }
    }
}

struct ChosenDriverView: View {
    let driverName: String
    @Environment(\.dismiss) private var dismiss

    var body: some View {
        VStack(spacing: 24) {
            Spacer()

            Image(systemName: "person.circle.fill")
                .font(.system(size: 100))
                .foregroundColor(.orange)

            Text("Motorista da Vez")
                .font(.title2)

            Text(driverName)
                .font(.largeTitle)
                .fontWeight(.bold)
                .foregroundColor(.orange)

            Spacer()

            Button(action: { dismiss() }) {
                Text("Sortear Novamente")
                    .frame(maxWidth: .infinity)
                    .padding()
            }
            .buttonStyle(.borderedProminent)

            NavigationLink(destination: HomeView()) {
                Text("Início")
                    .frame(maxWidth: .infinity)
                    .padding()
            }
            .buttonStyle(.bordered)
        }
        .padding(24)
    }
}

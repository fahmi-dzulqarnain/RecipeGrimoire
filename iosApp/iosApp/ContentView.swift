import SwiftUI
import SharedLogic

struct ContentView: View {
    @StateObject private var viewModel = ViewModel()

    var body: some View {
        NavigationStack {
            VStack(alignment: .center) {
                switch onEnum(of: viewModel.uiState) {
                case .loading:
                        ProgressView()
                        Text("Loading...")
                case .error(let error):
                        Text("Error: \(error.message)")
                case .success(let success):
                    ForEach(success.data, id: \.id) { meal in
                        HStack {
                            NavigationLink {
                                // RecipeDetailView(meal: meal, viewModel: viewModel)
                            } label: {
                                HStack {
                                    // Image Later

                                    VStack(alignment: .leading) {
                                        Text(meal.name)
                                        if let country = meal.country {
                                            Text(country)
                                        }
                                    }
                                    .padding(.horizontal, 16)
                                    .padding(.vertical, 8)
                                }
                            }

                            Spacer()

                            Button {
                                viewModel.toggleFavorite(meal)
                            } label: {
                                Image(systemName: viewModel.favoriteIds.contains(meal.id) ? "star.fill" : "star")
                            }
                            .padding(.horizontal, 16)
                        }
                    }
                }
            }
            .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
            .padding()
            .task {
                viewModel.startObserving()
            }
        }
    }
}

extension ContentView {
    @MainActor
    class ViewModel: ObservableObject {
        let viewModel: MealViewModel

        @Published private(set) var uiState: UiState
        @Published private(set) var favoriteIds: Set<String>
        private var uiStateObservationTask: Task<Void, Never>?
        private var favoriteIdsObservationTask: Task<Void, Never>?

        init() {
            self.viewModel = MealViewModel()
            self.uiState = viewModel.uiState.value
            self.favoriteIds = viewModel.favoriteIDs.value
        }

        func startObserving() {
            uiStateObservationTask = Task { [weak self] in
                guard let flow = self?.viewModel.uiState else {
                    return
                }
                for await state in flow {
                    guard let self else { break }
                    self.uiState = state
                }
            }

            favoriteIdsObservationTask = Task { [weak self] in
                guard let flow = self?.viewModel.favoriteIDs else {
                    return
                }
                for await ids in flow {
                    guard let self else { break }
                    self.favoriteIds = ids
                }
            }
        }

        func toggleFavorite(_ meal: MealModel) {
            viewModel.toggleFavorite(meal: meal)
        }

        deinit {
            uiStateObservationTask?.cancel()
            favoriteIdsObservationTask?.cancel()
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
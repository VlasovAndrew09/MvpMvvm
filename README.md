# MvpMvvm

Собственная реализация паттернов MVP и MVVM. Каждая реализация лежит в своей ветке.

Механизм сохранения presenter/viewModel при изменении конфигурации реализован с помощью [onRetainCustomNonConfigurationInstance](https://developer.android.com/reference/androidx/activity/ComponentActivity#onRetainCustomNonConfigurationInstance()).
Подсмотрел в этой [статье](https://mahendranv.github.io/posts/viewmodel-store/#:~:text=How%20ViewModel%20survives%20orientation%20change%3F).

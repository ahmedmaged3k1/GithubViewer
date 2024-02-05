import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.githubviewer.data.local.dto.RepoDetailsLocal
import com.example.githubviewer.domain.usecases.repos.ReposUseCases
import com.example.githubviewer.presentation.details.DetailsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class DetailsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var reposUseCases: ReposUseCases

    private lateinit var viewModel: DetailsViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        viewModel = DetailsViewModel(reposUseCases)
    }


    @Test
    fun `getRepoDetails should return default RepoDetailsLocal when owner or repo is null`() =
        runTest {
            val owner: String? = null
            val repo: String? = null
            val result = viewModel.getRepoDetails(owner, repo)
            assertEquals(RepoDetailsLocal(), result)
            assertEquals(RepoDetailsLocal(), viewModel.loadedRepoDetails)
        }

    @Test
    fun `getRepoDetails should return default RepoDetailsLocal when ReposUseCases returns null`() =
        runTest {
            val owner = "owner"
            val repo = "repo"
            `when`(reposUseCases.getReposDetails?.let { it(owner, repo) }).thenReturn(
                null
            )
            val result = viewModel.getRepoDetails(owner, repo)
            assertEquals(RepoDetailsLocal(), result)
            assertEquals(RepoDetailsLocal(), viewModel.loadedRepoDetails)
        }
}

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.githubviewer.data.remote.dto.RepoDetailsResponse
import com.example.githubviewer.domain.model.Owner
import com.example.githubviewer.domain.usecases.repos.ReposUseCases
import com.example.githubviewer.presentation.details.DetailsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)

class DetailsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = StandardTestDispatcher()

    @Mock
    lateinit var repoUseCases: ReposUseCases

    @InjectMocks
    private lateinit var detailsViewModel: DetailsViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun test_GetRepoDetails_Error() = runTest {
        val owner = "exampleOwner"
        val repoName = "exampleRepo"
        val sut = DetailsViewModel(repoUseCases)
        val result = sut.getRepoDetails(owner, repoName)
        delay(1000)
        Assert.assertEquals(result.owner.login, "No login available")
        Assert.assertEquals(result.owner, Owner())

    }


    @Test
    fun test_Empty_RepoDetails() = runTest {
        val sut = DetailsViewModel(repoUseCases)
        sut.getRepoDetails("", "")
        delay(1000)
        Assert.assertEquals(sut.loadedRepoDetails, RepoDetailsResponse())
    }


}

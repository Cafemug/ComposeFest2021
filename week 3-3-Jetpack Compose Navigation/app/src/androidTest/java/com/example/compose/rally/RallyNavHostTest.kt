import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import org.junit.Assert.assertEqualsimport kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


class RallyNavHostTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    lateinit var navController: NavHostController

    @Before
    fun setupRallyNavHost() {
        composeTestRule.setContent {
            navController = rememberNavController()
            RallyNavHost(navController = navController)
        }
    }

    @Test
    fun rallyNavHost() {
        composeTestRule
            .onNodeWithContentDescription("Overview Screen")
            .assertIsDisplayed()
    }

    @Test
    fun rallyNavHost_navigateToAllAccounts_viaUI() {
        composeTestRule
            .onNodeWithContentDescription("All Accounts")
            .performClick()
        composeTestRule
            .onNodeWithContentDescription("Accounts Screen")
            .assertIsDisplayed()
    }


    @Test
    fun rallyNavHost_navigateToBills_viaUI() {
        // When click on "All Bills"
        composeTestRule.onNodeWithContentDescription("All Bills").apply {
            performScrollTo()
            performClick()
        }
        // Then the route is "Bills"
        val route = navController.currentBackStackEntry?.destination?.route
        assertEquals(route, "Bills")
    }

    @Test
    fun rallyNavHost_navigateToAllAccounts_callingNavigate() {
        runBlocking {
            withContext(Dispatchers.Main) {
                navController.navigate(RallyScreen.Accounts.name)
            }
        }
        composeTestRule
            .onNodeWithContentDescription("Accounts Screen")
            .assertIsDisplayed()
    }
    runBlocking {
        withContext(Dispatchers.Main) {
            navController.navigate(RallyScreen.Accounts.name)
        }
    }
}
package com.example.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.platform.LocalContext
import com.example.AriseApplication
import com.example.ui.dashboard.DashboardScreen
import com.example.ui.dashboard.DashboardViewModel
import com.example.ui.habits.AddHabitScreen
import com.example.ui.habits.AddHabitViewModel
import com.example.ui.habits.HabitTrackerScreen
import com.example.ui.habits.HabitTrackerViewModel
import com.example.ui.study.StudyTrackerScreen
import com.example.ui.study.StudyTrackerViewModel
import com.example.ui.water.WaterTrackerScreen
import com.example.ui.water.WaterTrackerViewModel
import com.example.ui.workout.WorkoutTrackerScreen
import com.example.ui.workout.WorkoutTrackerViewModel

@Composable
fun AriseNavGraph() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val application = context.applicationContext as AriseApplication
    val habitRepository = application.habitRepository
    val waterRepository = application.waterRepository
    val studyRepository = application.studyRepository
    val workoutRepository = application.workoutRepository
    
    NavHost(
        navController = navController,
        startDestination = DashboardRoute
    ) {
        composable<DashboardRoute> {
            val dashboardViewModel: DashboardViewModel = viewModel(
                factory = DashboardViewModel.Factory(habitRepository)
            )
            DashboardScreen(
                viewModel = dashboardViewModel,
                onNavigateToHabits = { navController.navigate(HabitTrackerRoute) },
                onNavigateToWater = { navController.navigate(WaterTrackerRoute) },
                onNavigateToStudy = { navController.navigate(StudyTrackerRoute) },
                onNavigateToWorkout = { navController.navigate(WorkoutTrackerRoute) }
            )
        }

        composable<HabitTrackerRoute> {
            val habitTrackerViewModel: HabitTrackerViewModel = viewModel(
                factory = HabitTrackerViewModel.Factory(habitRepository)
            )
            HabitTrackerScreen(
                viewModel = habitTrackerViewModel,
                onNavigateBack = { navController.popBackStack() },
                onNavigateToAddHabit = { navController.navigate(AddHabitRoute) }
            )
        }

        composable<AddHabitRoute> {
            val addHabitViewModel: AddHabitViewModel = viewModel(
                factory = AddHabitViewModel.Factory(habitRepository)
            )
            AddHabitScreen(
                viewModel = addHabitViewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable<WaterTrackerRoute> {
            val waterTrackerViewModel: WaterTrackerViewModel = viewModel(
                factory = WaterTrackerViewModel.Factory(waterRepository)
            )
            WaterTrackerScreen(
                viewModel = waterTrackerViewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable<StudyTrackerRoute> {
            val studyTrackerViewModel: StudyTrackerViewModel = viewModel(
                factory = StudyTrackerViewModel.Factory(studyRepository)
            )
            StudyTrackerScreen(
                viewModel = studyTrackerViewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable<WorkoutTrackerRoute> {
            val workoutTrackerViewModel: WorkoutTrackerViewModel = viewModel(
                factory = WorkoutTrackerViewModel.Factory(workoutRepository)
            )
            WorkoutTrackerScreen(
                viewModel = workoutTrackerViewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}

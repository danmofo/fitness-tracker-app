import Box from "@/components/layout/Box";
import ScreenLayout from "@/components/layout/ScreenLayout";
import { useWorkoutStore } from "@/store/workout-store";
import { Text } from "react-native";

export default function WorkoutAddExerciseScreen() {
    const workoutStore = useWorkoutStore();

    return (
        <ScreenLayout screenHasHeader={false}>
            <Box padding={20}>
                <Text>The current exercise is {workoutStore.currentExercise?.name}</Text>
            </Box>
        </ScreenLayout>
    )
}
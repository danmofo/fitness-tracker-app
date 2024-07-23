import Button from "@/components/Button";
import Box from "@/components/layout/Box";
import ScreenLayout from "@/components/layout/ScreenLayout";
import { useWorkoutStore } from "@/store/workout-store";
import { Link, Stack, useLocalSearchParams, useRouter } from "expo-router";
import { useEffect } from "react";
import { Text } from "react-native";

export default function WorkoutAddExerciseScreen() {
    const workoutStore = useWorkoutStore();
    const router = useRouter();
    const params = useLocalSearchParams()

    useEffect(() => {
        router.setParams({
            title: workoutStore.currentExercise?.name
        });
    }, []);
    
    return (
        <ScreenLayout screenHasHeader={true}>
            <Stack.Screen 
                options={{
                    title: params.title
                }} 
            />
            <Box padding={20}>
                <Text>The current exercise is {workoutStore.currentExercise?.name}</Text>

                <Button title="Add set" href="/log-workout/add-exercise-to-workout" />
                
                <Text>Summary of exercises goes here....</Text>
            </Box>
        </ScreenLayout>
    )
}
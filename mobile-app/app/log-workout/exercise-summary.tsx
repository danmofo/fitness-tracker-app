import Button from "@/components/Button";
import Box from "@/components/layout/Box";
import ScreenLayout from "@/components/layout/ScreenLayout";
import CompletedSets from "@/components/log-workout/CompletedSets";
import { useWorkoutStore } from "@/store/workout-store";
import { Link, Stack, useLocalSearchParams, useRouter } from "expo-router";
import { useEffect } from "react";
import { Text } from "react-native";

export default function ExerciseSummaryScreen() {
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
                <Button title="Add set" href="/log-workout/add-exercise-to-workout" />
                
                <CompletedSets exercise={workoutStore.currentExercise} />
            </Box>
        </ScreenLayout>
    )
}
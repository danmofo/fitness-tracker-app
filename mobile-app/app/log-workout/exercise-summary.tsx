import Button from "@/lib/components/Button";
import Box from "@/lib/components/layout/Box";
import ScreenLayout from "@/lib/components/layout/ScreenLayout";
import CompletedSets from "@/lib/components/log-workout/CompletedSets";
import { useWorkoutStore } from "@/lib/store/workout-store";
import { Stack, useLocalSearchParams, useRouter } from "expo-router";
import { useEffect } from "react";

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
                
                <CompletedSets 
                    exercise={workoutStore.currentExercise} 
                    workoutId={workoutStore.workoutId}
                />
            </Box>
        </ScreenLayout>
    )
}
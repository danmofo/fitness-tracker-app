import { CompletedSet, listCompletedSetsForExercise } from "@/lib/api/workout";
import Button from "@/lib/components/Button";
import Box from "@/lib/components/layout/Box";
import ScreenLayout from "@/lib/components/layout/ScreenLayout";
import CompletedSets from "@/lib/components/log-workout/CompletedSets";
import { useAuthStore } from "@/lib/store/auth-store";
import { useWorkoutStore } from "@/lib/store/workout-store";
import { Stack, useLocalSearchParams, useRouter } from "expo-router";
import { useEffect, useState } from "react";

export default function ExerciseSummaryScreen() {
    const workoutStore = useWorkoutStore();
    const router = useRouter();
    const params = useLocalSearchParams()
    const authStore = useAuthStore();
    const [completedSets, setCompletedSets] = useState<CompletedSet[]>([]);

    useEffect(() => {
        router.setParams({
            title: workoutStore.currentExercise?.name
        });

        async function init() {
            const { completedSets } = await listCompletedSetsForExercise({
                sessionToken: authStore.sessionToken,
                workoutId: workoutStore.workoutId!,
                exerciseId: workoutStore.currentExercise!.id
            });
            setCompletedSets(completedSets);
        }

        init();
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
                
                <CompletedSets completedSets={completedSets} />
            </Box>
        </ScreenLayout>
    )
}
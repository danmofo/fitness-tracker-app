import { listWorkoutExercises } from "@/lib/api/workout";
import Box from "@/lib/components/layout/Box";
import ScreenLayout from "@/lib/components/layout/ScreenLayout";
import CompletedSets from "@/lib/components/log-workout/CompletedSets";
import { useAuthStore } from "@/lib/store/auth-store";
import { useWorkoutStore } from "@/lib/store/workout-store";
import { useEffect } from "react";
import { Text } from "react-native";

export default function SummaryScreen() {
    const workoutId = useWorkoutStore(state => state.workoutId);
    const sessionToken = useAuthStore(state => state.sessionToken);

    useEffect(() => {
        async function init() {
            if (!workoutId) {
                return;
            }

            const { exercises } = await listWorkoutExercises({
                sessionToken,
                workoutId
            });

            console.log(exercises);
        }

        init();
    }, [])

    return (
        <ScreenLayout screenHasHeader={false}>
            <Box padding={20}>
                
            </Box>
        </ScreenLayout>
    )
}
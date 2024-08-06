import { ExerciseWithCompletedSets, listWorkoutExercises } from "@/lib/api/workout";
import Button from "@/lib/components/Button";
import Box from "@/lib/components/layout/Box";
import ScreenLayout from "@/lib/components/layout/ScreenLayout";
import CompletedSets from "@/lib/components/log-workout/CompletedSets";
import Heading from "@/lib/components/text/Heading";
import { useAuthStore } from "@/lib/store/auth-store";
import { useWorkoutStore } from "@/lib/store/workout-store";
import { useEffect, useState } from "react";
import { Text, View } from "react-native";

export default function SummaryScreen() {
    const workoutId = useWorkoutStore(state => state.workoutId);
    const sessionToken = useAuthStore(state => state.sessionToken);
    const [exercises, setExercises] = useState<ExerciseWithCompletedSets[]>([]);

    useEffect(() => {
        async function init() {
            if (!workoutId) {
                return;
            }

            const { exercises } = await listWorkoutExercises({
                sessionToken,
                workoutId
            });

            setExercises(exercises);
        }

        init();
    }, [])

    if (!exercises || !exercises.length) {
        return (
            <ScreenLayout screenHasHeader={false}>
                <Box padding={20}>
                    <Text>Loading summary</Text>
                </Box>
            </ScreenLayout>
        )
    }

    return (
        <ScreenLayout screenHasHeader={false}>
            <Box padding={20}>
                <Text>Here is your workout summary...</Text>
                {
                    exercises.map(exercise => {
                        return (
                            <View key={exercise.id}>
                                <Heading>{exercise.name}</Heading>
                                <CompletedSets completedSets={exercise.completed} />
                            </View>
                        )
                    })
                }

                <Button title="Add new exercise" href="/log-workout/select-exercise" />
            </Box>
        </ScreenLayout>
    )
}
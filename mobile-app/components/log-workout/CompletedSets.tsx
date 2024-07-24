import { Text, View } from "react-native"
import Heading from "../text/Heading"
import { Exercise } from "@/api/exercise"
import { useEffect, useState } from "react"
import { CompletedSet, listCompletedSetsForExercise } from "@/api/workout"
import { useAuthStore } from "@/store/auth-store"

type CompletedExercisesProps = {
    exercise: Exercise | null,
    workoutId: number | null
}

export default function CompletedSets({ exercise, workoutId }: CompletedExercisesProps) {
    const authStore = useAuthStore();
    const [completedSets, setCompletedSets] = useState<CompletedSet[]>([]);

    useEffect(() => {
        (async () => {
            const { completedSets } = await listCompletedSetsForExercise({
                sessionToken: authStore.sessionToken,
                workoutId: workoutId,
                exerciseId: exercise!.id
            });
            setCompletedSets(completedSets);
        })();
    }, [])

    return (
        <View>
            <Heading>Completed sets for {String(exercise?.name)}</Heading>
            <Text>There are {completedSets?.length} completed sets for this exercise</Text>
        </View>
    )
}

import { Text, View } from "react-native"
import Heading from "../text/Heading"
import { Exercise } from "@/api/exercise"

type CompletedExercisesProps = {
    exercise: Exercise | null
}

export default function CompletedSets({ exercise }: CompletedExercisesProps) {
    return (
        <View>
            <Heading>Completed sets for {String(exercise?.name)}</Heading>
        </View>
        
    )
}

import { Exercise } from "@/lib/api/exercise";
import { FlatList, Pressable, StyleSheet, Text, View } from "react-native"

type ExerciseListProps = {
    exercises: Exercise[],
    onSelectExercise: (exercise: Exercise) => void
}

export default function ExerciseList({ exercises, onSelectExercise }: ExerciseListProps) {
    if(!exercises) {
        return <Text>Loading exercises...</Text>
    }

    if(exercises?.length === 0) {
        return <Text>No exercises found</Text>;
    }

    return (
        <View style={styles.container}>
            <FlatList
                style={styles.exerciseList}
                data={exercises}
                renderItem={({ item: exercise }) => (
                    <Pressable
                        style={styles.exerciseListItem}
                        onPress={() => onSelectExercise(exercise)}>
                            <Text style={styles.exerciseListItemName}>
                                {exercise.name}
                                {exercise.brand ? `(${exercise.brand})` : null}
                            </Text>
                    </Pressable>
                )}
            />
        </View>
    )
}

const styles = StyleSheet.create({
    container: {

    },
    exerciseList: {

    },
    exerciseListItem: {
        borderBottomWidth: 1,
        padding: 20,
        borderBottomColor: '#cccc'
    },
    exerciseListItemName: {
        fontSize: 18
    }
});
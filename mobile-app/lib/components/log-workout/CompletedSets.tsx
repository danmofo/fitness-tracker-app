import { FlatList, Pressable, StyleSheet, Text, View } from "react-native"
import { CompletedSet } from "@/lib/api/workout"

type CompletedExercisesProps = {
    completedSets: CompletedSet[]
}

export default function CompletedSets({ completedSets }: CompletedExercisesProps) {
    return (
        <View>
            <View style={styles.tableHeader}>
                <Text style={styles.tableHeaderCol}>Sets</Text>
                <Text style={styles.tableHeaderCol}>Reps</Text>
                <Text style={styles.tableHeaderCol}>Weight</Text>
            </View>
            <FlatList
                style={{}}
                data={completedSets}
                renderItem={({ item: completedSet }) => (
                    <Pressable
                        style={styles.tableRow}
                        onPress={() => {}}>
                            <Text style={styles.tableCol}>{completedSet.sets}</Text>
                            <Text style={styles.tableCol}>{completedSet.reps}</Text>
                            <Text style={styles.tableCol}>{completedSet.weight}kg</Text>
                    </Pressable>
                )}
            />
        </View>
    )
}


const styles = StyleSheet.create({
    tableHeader: {
        flexDirection: 'row'
    },
    tableHeaderCol: {
        flex: 1,
        fontWeight: 'bold',
        fontSize: 24
    },
    tableCol: {
        flex: 1,
        fontSize: 18
    },
    tableRow: {
        flexDirection: 'row'
    }
});
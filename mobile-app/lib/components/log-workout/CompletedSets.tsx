import { FlatList, Pressable, StyleSheet, Text, View } from "react-native"
import { Exercise } from "@/lib/api/exercise"
import { useEffect, useState } from "react"
import { CompletedSet, listCompletedSetsForExercise } from "@/lib/api/workout"
import { useAuthStore } from "@/lib/store/auth-store"

type CompletedExercisesProps = {
    completedSets: CompletedSet[],
    exercise?: Exercise
}

export default function CompletedSets({ completedSets, exercise }: CompletedExercisesProps) {

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
import { Stack } from "expo-router";

export default function LogWorkoutLayout() {
    return (
        <Stack>
            <Stack.Screen 
                name="index"
                options={{
                    headerShown: false
                }}
            />
            <Stack.Screen 
                name="select-exercise"
                options={{
                    title: 'Select an exercise'
                }}
            />
            {/* workout-add-exercise defines it's own <Stack.Screen> inside of its route file. */}

            <Stack.Screen 
                name="add-exercise-to-workout"
                options={{
                    title: 'Add sets for exercise'
                }}
            />

            <Stack.Screen 
                name="summary"
                options={{
                    headerShown: false
                }}
            />
        </Stack>
    )
}
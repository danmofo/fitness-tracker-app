import { startWorkout } from "@/lib/api/workout";
import Button from "@/lib/components/Button";
import Box from "@/lib/components/layout/Box";
import ScreenLayout from "@/lib/components/layout/ScreenLayout";
import Heading from "@/lib/components/text/Heading";
import { useAuthStore } from "@/lib/store/auth-store";
import { useWorkoutStore } from "@/lib/store/workout-store";
import { router } from "expo-router";
import { useState } from "react";
import { Alert, Text, View } from "react-native";


export default function LogWorkoutScreen() {
    const workoutStore = useWorkoutStore();
    const [hasWorkoutInProgress] = useState(workoutStore.hasWorkoutInProgress());

    return (
        <ScreenLayout screenHasHeader={false}>
            <Box padding={20}>
                <Heading>Log workout</Heading>
                {
                    hasWorkoutInProgress ?
                    <WorkoutInProgress /> :
                    <StartNewWorkout />
                }
                
            </Box>
        </ScreenLayout>
    )
}

function StartNewWorkout() {
    const sessionToken = useAuthStore(state => state.sessionToken);
    const workoutStore = useWorkoutStore();

    async function handleStartWorkout() {
        console.log('handleStartWorkout()');

        const { workoutId } = await startWorkout({ sessionToken });
        console.log(workoutId);
        if(workoutId === null) {
            Alert.alert('Failed to start workout, please try again.');
            return;
        }

        workoutStore.startWorkout(workoutId!);

        router.navigate('/log-workout/select-exercise');
    }
    
    return <Button title="Start new workout" onPress={handleStartWorkout} />
}

function WorkoutInProgress() {
    const workoutStore = useWorkoutStore();

    async function handleResumeWorkout() {
        router.navigate('/log-workout/summary');
    }

    return (
        <View>
            <Text>There's already a workout in progress!</Text>
            <Button title="Resume" onPress={handleResumeWorkout} />
        </View>
    )
}
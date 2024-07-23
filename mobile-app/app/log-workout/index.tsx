import { startWorkout } from "@/api/workout";
import Button from "@/components/Button";
import Box from "@/components/layout/Box";
import ScreenLayout from "@/components/layout/ScreenLayout";
import Heading from "@/components/text/Heading";
import { useAuthStore } from "@/store/auth-store";
import { useWorkoutStore } from "@/store/workout-store";
import { router } from "expo-router";
import { Alert } from "react-native";


export default function LogWorkoutScreen() {
    const sessionToken = useAuthStore(state => state.sessionToken);
    const workoutStore = useWorkoutStore();

    async function handleStartWorkout() {
        console.log('handleStartWorkout()');

        if(workoutStore.hasWorkoutInProgress()) {
            console.log('Workout already started.');
            router.navigate('/log-workout/select-exercise');
            return;
        }

        const { workoutId } = await startWorkout({ sessionToken });
        console.log(workoutId);
        if(workoutId === null) {
            Alert.alert('Failed to start workout, please try again.');
            return;
        }

        workoutStore.startWorkout(workoutId!);

        router.navigate('/log-workout/select-exercise');
    }

    return (
        <ScreenLayout screenHasHeader={false}>
            <Box padding={20}>
                <Heading>Log workout</Heading>
                <Button title="START" onPress={handleStartWorkout} />
            </Box>
        </ScreenLayout>
    )
}
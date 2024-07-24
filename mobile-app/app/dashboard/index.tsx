import Button from "@/components/Button";
import Box from "@/components/layout/Box";
import ScreenLayout from "@/components/layout/ScreenLayout";
import Heading from "@/components/text/Heading";
import { useAuthStore } from "@/store/auth-store";
import { useWorkoutStore } from "@/store/workout-store";
import { router } from "expo-router";
import { Text } from "react-native";

export default function DashboardScreen() {
    const authStore = useAuthStore();
    const workoutStore = useWorkoutStore();

    function handleLogOut() {
        authStore.logOut();
        workoutStore.clear();
        
        router.navigate('/');
    }

    return (
        <ScreenLayout screenHasHeader={false}>
            <Box padding={20}>
                <Heading>Log workout</Heading>
                <Text>Here you can log your workout</Text>
                <Button title="Start workout" href="/log-workout/" />
            </Box>
            <Box padding={20}>
                <Heading>Log weight</Heading>
                <Text>Here you can log your weight</Text>
                <Button title="Log weight" href="/log-weight/" />
            </Box>
            <Box padding={20} paddingVertical={40}>
                <Button title="Log out" onPress={handleLogOut} />
            </Box>
        </ScreenLayout>
    )
}
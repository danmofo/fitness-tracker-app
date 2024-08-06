import Button from "@/lib/components/Button";
import Box from "@/lib/components/layout/Box";
import ScreenLayout from "@/lib/components/layout/ScreenLayout";
import Heading from "@/lib/components/text/Heading";
import { useAuthStore } from "@/lib/store/auth-store";
import { useWorkoutStore } from "@/lib/store/workout-store";
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
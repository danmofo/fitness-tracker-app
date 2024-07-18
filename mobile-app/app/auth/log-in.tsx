import LogInForm from "@/components/auth/LogInForm";
import Box from "@/components/layout/Box";
import ScreenLayout from "@/components/layout/ScreenLayout";
import Heading from "@/components/text/Heading";
import { router } from "expo-router";

export default function LogInScreen() {
    function handleAuthSuccess() {
        router.replace('/dashboard');
    }

    return (
        <ScreenLayout screenHasHeader={false}>
            <Box padding={20}>
                <Heading textAlign="center">Log in to your account to get started</Heading>

                <LogInForm onAuthSuccess={handleAuthSuccess} />
            </Box>
        </ScreenLayout>
    )
}
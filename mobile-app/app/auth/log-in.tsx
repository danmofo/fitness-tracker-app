import LogInForm from "@/components/auth/LogInForm";
import Box from "@/components/layout/Box";
import ScreenLayout from "@/components/layout/ScreenLayout";
import Heading from "@/components/text/Heading";

export default function LogInScreen() {
    return (
        <ScreenLayout screenHasHeader={false}>
            <Box padding={20}>
                <Heading textAlign="center">Log in to your account to get started</Heading>

                <LogInForm />
            </Box>
        </ScreenLayout>
    )
}
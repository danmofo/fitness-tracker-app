import Button from "@/lib/components/Button";
import Box from "@/lib/components/layout/Box";
import ScreenLayout from "@/lib/components/layout/ScreenLayout";
import Heading from "@/lib/components/text/Heading";
import { useAuthStore } from "@/lib/store/auth-store";
import { Redirect, router } from "expo-router";
import { Text } from "react-native";

export default function HomepageScreen() {
    const sessionToken = useAuthStore(state => state.sessionToken);
    if(sessionToken) {
        return (<Redirect href="/dashboard/" />);
    }

    return (
        <ScreenLayout screenHasHeader={false}>
            <Box padding={20}>
                <Heading>Welcome to Fitness Tracker App!</Heading>
                <Text>This section will describe in a few words what you can do on this app.</Text>
                <Text>To get started, either log in or sign up:</Text>
            </Box>
            <Box flex={1} padding={20} clampChildrenToBottom>
                <Button title="Log in" href="/auth/log-in" />
                <Button title="Sign up" href="/auth/sign-up" />
            </Box>
        </ScreenLayout>
    );
}

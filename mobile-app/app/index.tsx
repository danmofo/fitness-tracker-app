import Button from "@/components/Button";
import Box from "@/components/layout/Box";
import ScreenLayout from "@/components/layout/ScreenLayout";
import Heading from "@/components/text/Heading";
import { router } from "expo-router";
import { Text } from "react-native";

export default function HomepageScreen() {
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

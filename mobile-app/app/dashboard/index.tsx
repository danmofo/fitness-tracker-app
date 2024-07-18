import Button from "@/components/Button";
import Box from "@/components/layout/Box";
import ScreenLayout from "@/components/layout/ScreenLayout";
import Heading from "@/components/text/Heading";
import { Text } from "react-native";

export default function DashboardScreen() {
    return (
        <ScreenLayout screenHasHeader={false}>
            <Box flex={1} padding={20}>
                <Heading>Log workout</Heading>
                <Text>Here you can log your workout</Text>
                <Button title="Start workout" href="start-workout" />
            </Box>
            <Box flex={1} padding={20}>
                <Heading>Log weight</Heading>
                <Text>Here you can log your weight</Text>
                <Button title="Log weight" href="/log-weight/" />
            </Box>
        </ScreenLayout>
    )
}
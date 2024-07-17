import Box from "@/components/layout/Box";
import ScreenLayout from "@/components/layout/ScreenLayout";
import { Text } from "react-native";

export default function LogInScreen() {
    return (
        <ScreenLayout screenHasHeader={false}>
            <Box padding={20}>
                <Text>Log in screen</Text>
            </Box>
        </ScreenLayout>
    )
}
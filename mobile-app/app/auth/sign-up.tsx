import Box from "@/lib/components/layout/Box";
import ScreenLayout from "@/lib/components/layout/ScreenLayout";
import { Text } from "react-native";

export default function SignUpScreen() {
    return (
        <ScreenLayout screenHasHeader={false}>
            <Box padding={20}>
                <Text>Sign up screen</Text>
            </Box>
        </ScreenLayout>
    )
}
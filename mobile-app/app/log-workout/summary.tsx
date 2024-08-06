import Box from "@/components/layout/Box";
import ScreenLayout from "@/components/layout/ScreenLayout";
import { Text } from "react-native";

export default function SummaryScreen() {
    return (
        <ScreenLayout screenHasHeader={false}>
            <Box padding={20}>
                <Text>Workout summary screen.</Text>
            </Box>
        </ScreenLayout>
    )
}
import Box from "@/components/layout/Box";
import ScreenLayout from "@/components/layout/ScreenLayout";
import { Text } from "react-native";

export default function AddExerciseToWorkoutScreen() {
    return (
        <ScreenLayout screenHasHeader={true}>
            <Box padding={20}>
                <Text>Form goes here...</Text>
            </Box>
        </ScreenLayout>
    )
}
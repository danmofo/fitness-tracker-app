import Box from "@/components/layout/Box";
import ScreenLayout from "@/components/layout/ScreenLayout";
import Heading from "@/components/text/Heading";
import { Text } from "react-native";

export default function LogWeightScreen() {
    return (
        <ScreenLayout screenHasHeader={false}>
            <Box padding={20}>
                <Heading>Log weight</Heading>
               <Text>Log your weight here</Text> 
            </Box>
        </ScreenLayout>
    )
}
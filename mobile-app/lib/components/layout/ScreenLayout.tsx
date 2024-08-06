import { StyleSheet, View } from "react-native";
import { SafeAreaView } from "react-native-safe-area-context";

type ScreenLayoutProps = {
    children: React.ReactNode,
    screenHasHeader?: boolean
}

export default function ScreenLayout({ children, screenHasHeader = true }: ScreenLayoutProps) {
    const Wrapper = screenHasHeader ? View : SafeAreaView;

    return (
        <Wrapper style={styles.container}>
            {children}
        </Wrapper>
    );
}

const styles = StyleSheet.create({
    container: {
        backgroundColor: '#FFF',
        flex: 1
    }
});
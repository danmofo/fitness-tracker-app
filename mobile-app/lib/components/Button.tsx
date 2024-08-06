import { Link } from "expo-router";
import { Pressable, StyleSheet, Text } from "react-native"

type ButtonProps = {
    title: string,
    onPress?: () => void,
    href?: string
}

export default function Button({ href, title, onPress = () => {} }: ButtonProps) {
    // If there's an href, wrap with expo-router's <Link> component to navigate.
    if(href) {
        return (
            <Link 
                href={href} 
                style={styles.buttonContainer} 
                asChild>
                <Pressable>
                    <Text style={styles.buttonText}>
                        {title}
                    </Text>
                </Pressable>
            </Link>
        )
    }

    // Otherwise use a <Pressable> with onPress
    return (
        <Pressable style={styles.buttonContainer} onPress={() => onPress()}>
            <Text style={styles.buttonText}>
                {title}
            </Text>
        </Pressable>
    )
}

const styles = StyleSheet.create({
    buttonContainer: {
        borderRadius: 3,
        backgroundColor: '#454545',
        paddingHorizontal: 24,
        paddingVertical: 14
    },
    buttonText: {
        color: '#FFF',
        textAlign: 'center',
        fontSize: 20
    }
});
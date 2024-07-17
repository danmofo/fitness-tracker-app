import { FlexStyle, StyleSheet, Text } from "react-native"

type HeadingProps = {
    children: string,
    layoutStyle?: FlexStyle
}

export default function Heading({ children, layoutStyle }: HeadingProps) {
    return <Text style={[styles.heading, layoutStyle]}>{children}</Text>
}

const styles = StyleSheet.create({
    heading: {
        fontSize: 24
    }
});
import { FlexStyle, StyleSheet, Text, TextProps, TextStyle } from "react-native"

type TextAlign = 'auto' | 'left' | 'right' | 'center' | 'justify' | undefined;

type HeadingProps = {
    children: string,
    textAlign?: TextAlign,
    layoutStyle?: FlexStyle
}

export default function Heading({ children, layoutStyle, textAlign}: HeadingProps) {
    return <Text style={[styles.heading, layoutStyle, {textAlign}]}>{children}</Text>
}

const styles = StyleSheet.create({
    heading: {
        fontSize: 24
    }
});
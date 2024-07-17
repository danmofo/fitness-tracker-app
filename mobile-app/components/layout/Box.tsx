import { View } from "react-native"

type BoxProps = {
    children: React.ReactNode
    padding?: number
    paddingHorizontal?: number
    paddingVertical?: number
}

export default function Box({ children, padding, paddingHorizontal, paddingVertical }: BoxProps) {
    const styles: any = {};

    if(padding) {
        styles.padding = padding;
    }

    if(paddingHorizontal) {
        styles.paddingHorizontal = paddingHorizontal;
    }

    if(paddingVertical) {
        styles.paddingVertical = paddingVertical;
    }

    return (
        <View 
            style={styles}>
            {children}
        </View>
    )
}
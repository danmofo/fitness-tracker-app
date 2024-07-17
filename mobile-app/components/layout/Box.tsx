import { View } from "react-native"

type BoxProps = {
    children: React.ReactNode,
    flex?: number,
    clampChildrenToBottom?: boolean,
    padding?: number
    paddingHorizontal?: number
    paddingVertical?: number
}

export default function Box({ children, clampChildrenToBottom, flex, padding, paddingHorizontal, paddingVertical }: BoxProps) {
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

    if(flex) {
        styles.flex = flex;
    }

    if(clampChildrenToBottom) {
        styles.justifyContent = 'flex-end';
        styles.alignItems = 'end';
    }

    return (
        <View 
            style={styles}>
            {children}
        </View>
    )
}
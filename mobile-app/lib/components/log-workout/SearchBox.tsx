import { StyleSheet, Text, TextInput, View } from "react-native"

type SearchBoxProps = {
    query: string,
    onChangeQuery: (newQuery: string) => void
}

export default function SearchBox({ query, onChangeQuery }: SearchBoxProps) {
    function handleClear() {
        onChangeQuery('');
    }

    return (
        <View style={styles.searchBoxContainer}>
            <TextInput
                style={styles.searchBox}
                defaultValue={query}
                onChangeText={onChangeQuery} 
                placeholder="Enter an exercise name..."
            />
            {
                query.length > 0 ?
                <View style={styles.clearButton}>
                    <Text style={styles.clearButtonText} onPress={handleClear}>Clear</Text>
                </View> :
                null
            }
        </View>
    )
}

const styles = StyleSheet.create({
    searchBox:  {
        borderWidth: 1,
        borderColor: '#ccc',
        padding: 20,
        paddingVertical: 10,
        fontSize: 20,
        flex: 1
    },
    clearButton: {
        padding: 20,
        backgroundColor: '#454545'
    },
    clearButtonText: {
        color: '#FFF'
    },
    searchBoxContainer:  {
        flexDirection: 'row'
    }
});
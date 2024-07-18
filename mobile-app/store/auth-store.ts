import { create } from "zustand";
import * as SecureStore from "expo-secure-store";
import { StateStorage, createJSONStorage, persist } from "zustand/middleware";

const secureStoreStorage: StateStorage = {
    getItem: async function (name: string) {
        return await SecureStore.getItemAsync(name);
    },
    setItem: async function (name: string, value: string) {
        return await SecureStore.setItemAsync(name, value);
    },
    removeItem: async function (name: string) {
        return await SecureStore.deleteItemAsync(name);
    }
}

export type AuthState = {
    sessionToken: string,
    saveSessionToken: (sessionToken: string) => void,
    logOut: () => void
}

export const useAuthStore = create(
    persist<AuthState>(
        (set) => ({
            sessionToken: '',
            saveSessionToken: (sessionToken: string) => {
                set({ sessionToken });
            },
            logOut: () => {
                set({ sessionToken: '' });
            }
        }),
        {
            name: 'auth-storage',
            storage: createJSONStorage(() => secureStoreStorage)
        }
    )
);
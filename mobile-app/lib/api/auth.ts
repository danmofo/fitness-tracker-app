import { API_BASE_URL } from "@/lib/config";
import { ErrorCode } from "./error-types";

export type AuthenticatedRequest = {
    sessionToken: string
}

type LoginRequest = {
    email: string,
    password: string
}

type ValidationError = {
    field: string,
    message: string
}

type ValidationErrorResponse = {
    errorCode?: ErrorCode,
    validationErrors?: ValidationError[]
}

type InvalidCredentialsResponse = {
    errorCode?: ErrorCode
}

type LoginSuccessResponse = {
    success?: boolean,
    sessionToken?: string
}

// Possible results:
// - Validation errors
// - Invalid email/password
// - Success!
type LoginResponse = LoginSuccessResponse & InvalidCredentialsResponse & ValidationErrorResponse;

export async function login(request: LoginRequest): Promise<LoginResponse> {
    try {
        const response = await fetch(`${API_BASE_URL}/auth/login`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(request)
        });

        const json = await response.json();

        // Extract the session token if the login request succeeded
        if (json.success) {
            const sessionToken = response.headers.get('X-Auth-Token');
            if (sessionToken) {
                json.sessionToken = sessionToken;
            }
        }

        return json;
    } catch (e) {
        return {
            success: false
        }
    }
}
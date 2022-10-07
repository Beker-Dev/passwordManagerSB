import { getCookie } from "typescript-cookie"
import { Vue } from "vue-class-component"

export class AuthUtils {
    
    public checkAuthenticated(): boolean {
        if (getCookie("access")) {
            return true
        }
        else {
            return false
        }
    }
}

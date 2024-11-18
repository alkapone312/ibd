export default interface LoginInterface {
    isLogged(): boolean;
    login(login: string, password: string): Promise<void>;
    register(login: string, password: string): Promise<void>;
    logout(): void;
    onLoginChange(callback: (isLogged: boolean) => void): void;

}
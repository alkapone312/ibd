export type Notification = {type: string, message: string};

export default class NotificationService {
    private static notifications: Notification[] = []; 
    private static notificationCallbacks: (() => void)[] = [];

    public static pushNotification(not: Notification) {
        this.notifications.push(not)
        this.notificationCallbacks.forEach((cb) => {
            cb();
        })
    }

    public static isNotification() {
        return !!this.notifications.length
    }

    public static popNotification(): Notification {
        return this.notifications.pop() ?? {type: '', message: ''};
    }

    public static onNotification(callback: () => void) {
        this.notificationCallbacks.push(callback);
    }
}
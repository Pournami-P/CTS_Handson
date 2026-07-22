import { Injectable } from '@angular/core';

/**
 * NotificationService can be provided at component level to create isolated instance state.
 */
@Injectable()
export class NotificationService {
  private notifications: string[] = ['Welcome to Student Course Portal!'];

  getNotifications(): string[] {
    return [...this.notifications];
  }

  addNotification(message: string): void {
    this.notifications.push(message);
  }

  clear(): void {
    this.notifications = [];
  }
}

export { NotificationService as Notification };

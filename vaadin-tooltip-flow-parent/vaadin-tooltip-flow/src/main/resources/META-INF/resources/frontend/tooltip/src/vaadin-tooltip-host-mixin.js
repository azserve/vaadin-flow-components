/**
 * @license
 * Copyright (c) 2017 - 2022 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */
import { ControllerMixin } from '@vaadin/component-base/src/controller-mixin.js';
import { TooltipController } from './vaadin-tooltip-controller.js';

/**
 * A mixin enabling a web component to act as a tooltip host.
 * Any components that extend this mixin are required to import
 * the `vaadin-tooltip` web component using the correct theme.
 *
 * @polymerMixin
 */
export const TooltipHostMixin = (superClass) =>
  class TooltipHostMixinClass extends ControllerMixin(superClass) {
    static get properties() {
      return {
        /**
         * The delay in milliseconds before the tooltip
         * is closed, when not using manual mode.
         * This only applies to `mouseleave` listener.
         * On blur, the tooltip is closed immediately.
         * @attr {number} tooltip-cooldown
         */
        tooltipCooldown: {
          type: Number,
          observer: '__tooltipCooldownChanged',
        },

        /**
         * The delay in milliseconds before the tooltip
         * is opened, when not using manual mode.
         * This only applies to `mouseenter` listener.
         * On focus, the tooltip is opened immediately.
         * @attr {number} tooltip-delay
         */
        tooltipDelay: {
          type: Number,
          observer: '__tooltipDelayChanged',
        },

        /**
         * When true, the tooltip is controlled manually
         * instead of reacting to focus and mouse events.
         * @attr {boolean} tooltip-manual
         */
        tooltipManual: {
          type: Boolean,
          value: false,
          observer: '__tooltipManualChanged',
        },

        /**
         * When true, the tooltip is opened programmatically.
         * Only works if `tooltipManual` is set to `true`.
         * @attr {boolean} tooltip-opened
         */
        tooltipOpened: {
          type: Boolean,
          value: false,
          observer: '__tooltipOpenedChanged',
        },

        /**
         * Position of the tooltip with respect to the element.
         * Supported values: `top`, `bottom`, `start`, `end`.
         * @attr {string} tooltip-position
         */
        tooltipPosition: {
          type: String,
          observer: '__tooltipPositionChanged',
        },

        /**
         * String used as a content for the tooltip
         * shown on the element when it gets focus
         * or is hovered using the pointer device.
         * @attr {string} tooltip-text
         */
        tooltipText: {
          type: String,
          observer: '__tooltipTextChanged',
        },

        /**
         * An HTML element to attach the tooltip to.
         * Defaults to the the web component itself.
         * Override to use another element instead.
         * @protected
         */
        _tooltipTarget: {
          type: Object,
          observer: '__tooltipTargetChanged',
        },
      };
    }

    constructor() {
      super();

      this._tooltipTarget = this;
      this._tooltipController = new TooltipController(this);
    }

    /** @protected */
    ready() {
      super.ready();

      this.addController(this._tooltipController);
    }

    /** @private */
    __tooltipCooldownChanged(cooldown) {
      this._tooltipController.setCooldown(cooldown);
    }

    /** @private */
    __tooltipDelayChanged(delay) {
      this._tooltipController.setDelay(delay);
    }

    /** @private */
    __tooltipManualChanged(manual, oldManual) {
      if (manual || oldManual) {
        this._tooltipController.setManual(manual);
      }
    }

    /** @private */
    __tooltipOpenedChanged(opened, oldOpened) {
      if (opened || oldOpened) {
        this._tooltipController.setOpened(opened);
      }
    }

    /** @private */
    __tooltipPositionChanged(position, oldPosition) {
      if (position || oldPosition) {
        this._tooltipController.setPosition(position);
      }
    }

    /** @private */
    __tooltipTextChanged(tooltipText, oldTooltipText) {
      if (tooltipText || oldTooltipText) {
        this._tooltipController.setTooltipText(tooltipText);
      }
    }

    /** @private */
    __tooltipTargetChanged(target, oldTarget) {
      if (target || oldTarget) {
        this._tooltipController.setTarget(target);
      }
    }
  };

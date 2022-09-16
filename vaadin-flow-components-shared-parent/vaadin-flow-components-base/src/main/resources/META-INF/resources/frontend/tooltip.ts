import { Tooltip } from '@vaadin/tooltip';

const Vaadin = (window as any).Vaadin ||= {};
const Flow = Vaadin.Flow ||= {};

Flow.tooltip = {
  setDefaultHideDelay: (hideDelay: number) => Tooltip.setDefaultHideDelay(hideDelay)
}

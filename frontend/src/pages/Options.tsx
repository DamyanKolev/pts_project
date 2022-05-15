import {
  ProductSwitch,
  ProductSwitchItem,
  Title,
} from "@ui5/webcomponents-react";
import "@ui5/webcomponents-icons/dist/AllIcons.js";

export default function Options() {
  const currentURL = window.location.href;

  return (
    <div className="page-container">
      <div className="title-container">
        <Title level="H3"> Select Option</Title>
      </div>

      <ProductSwitch>
        <ProductSwitchItem
          titleText="Analysis Files"
          subtitleText="Reading and Summarizing"
          icon="crossed-line-chart"
          targetSrc={`${currentURL}/RAS`}
        />
        <ProductSwitchItem
          titleText="Frequency distribution"
          subtitleText="Frequency distribution"
          icon="business-objects-experience"
          targetSrc={`${currentURL}/FD`}
        />
        <ProductSwitchItem
          titleText="Central Trend"
          subtitleText="Measures of the central trend"
          icon="crossed-line-chart"
          targetSrc={`${currentURL}/MCT`}
        />
        <ProductSwitchItem
          titleText="Distraction measures"
          subtitleText="Distraction measures"
          icon="business-objects-experience"
          targetSrc={`${currentURL}/DM`}
        />

        <ProductSwitchItem
          titleText="Correlation"
          subtitleText="Correlation analysis"
          icon="crossed-line-chart"
          targetSrc={`${currentURL}/CA`}
        />
      </ProductSwitch>
    </div>
  );
}

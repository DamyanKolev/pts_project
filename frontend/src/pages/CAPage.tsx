import { ResponsiveGridLayout, Title } from "@ui5/webcomponents-react";
import { LineChart } from "@ui5/webcomponents-react-charts";
import React from "react";

interface CAData {
  dependence: string;
  dataset: { assessment: number; wikiUpdates: number }[];
}

export default function CAPage() {
  const [data, setData] = React.useState<CAData>();
  const [loading, setLoading] = React.useState<boolean>(true);

  const showLoading = () => setLoading(true);

  const hideLoading = () => setLoading(false);

  React.useEffect(() => {
    const fetchData = async () => {
      showLoading();
      await fetch("http://localhost:8080/api/options/CA", {})
        .then((res) => {
          return res.json();
        })
        .then((json) => {
          setData(json as any as CAData);
        });
      hideLoading();
    };
    fetchData().catch(console.error);
  }, [loading]);

  return (
    <div className="page-container">
      <div className="page-bar">
        <Title level="H3">Корелационен анализ</Title>
      </div>

      <div className="option-content">
        <ResponsiveGridLayout>
          <div
            style={{ gridColumn: "span 8" }}
            className="responsiveGridLayoutItem"
          >
            <LineChart
              dimensions={[{ accessor: "assessment" }]} //"wikiUpdates"
              measures={[{ accessor: "wikiUpdates", label: "Оценка" }]} //"assessment"
              dataset={data?.dataset}
            />
          </div>

          <div
            style={{ gridColumn: "span 3" }}
            className="responsiveGridLayoutItem"
          >
            <div className="page-data-container">
              <Title level="H5">Зависимост: </Title>
              <Title level="H5">{data?.dependence}</Title>
            </div>
          </div>
        </ResponsiveGridLayout>
      </div>
    </div>
  );
}

import {
  ResponsiveGridLayout,
  Title,
} from "@ui5/webcomponents-react";
import { LineChart } from "@ui5/webcomponents-react-charts";
import React from "react";

interface CAData {
  dependence: string;
  dataset: { assessment: number; wikiUpdates: number }[];
}

export default function CAPage() {
  const [data, setData] = React.useState<CAData>();

  React.useEffect(() => {
    const fetchData = async () => {
      await fetch("http://localhost:8080/api/options/CA", {
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
          "Access-Control-Allow-Origin": "*",
        },
      })
        .then((res) => {
          return res.json();
        })
        .then((json) => {
          setData(json as any as CAData);
        });
    };
    fetchData().catch(console.error);
  }, []);

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
              dimensions={[{ accessor: "assessment" }]} //wiki
              measures={[{ accessor: "wikiUpdates", label: "Wiki updates" }]} //ocenka
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

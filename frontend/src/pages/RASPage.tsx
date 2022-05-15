import {
  Button,
  FileUploader,
  ResponsiveGridLayout,
  Title,
} from "@ui5/webcomponents-react";
import React from "react";
import "@ui5/webcomponents/dist/features/InputElementsFormSupport.js";

export default function RASPage() {
  const [file, setFile] = React.useState<File>();

  // const fetchData = async () => {
  //   await fetch("http://localhost:8080//api/options/RAS", {
  //     mode: "cors",
  //     method: 'POST',
  //   })
  //     .then((res) => res.json)
  //     .then((json) => {
  //       setData(json as any as Array<FDData>);
  //     });
  // };

  return (
    <div className="page-container">
      <div className="page-bar">
        <Title level="H3">Обобщение на Файлове</Title>
      </div>

      <div className="option-content">
        <ResponsiveGridLayout>
          <div
            style={{ gridColumn: "span 3" }}
            className="responsiveGridLayoutItem"
          ></div>
        </ResponsiveGridLayout>
      </div>
    </div>
  );
}

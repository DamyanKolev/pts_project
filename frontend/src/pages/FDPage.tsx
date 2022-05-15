import {
  Label,
  Table,
  TableCell,
  TableColumn,
  TableRow,
  Title,
} from "@ui5/webcomponents-react";
import React from "react";

interface FDData {
  exercise: string;
  absoluteFrequency: number;
  relativeFrequency: number;
}

export default function FDPage() {
  const [data, setData] = React.useState<FDData[]>([]);
  const [loading, setLoading] = React.useState<boolean>(true)

  const showLoading = () => (
      setLoading(true)
  )

  const hideLoading = () => (
      setLoading(false)
  )

  React.useEffect(() => {
    const fetchData = async () => {
      showLoading()
      await fetch("http://localhost:8080/api/options/FD", {
      })
        .then((res) => res.json())
        .then((json) => {
          setData(json as any as Array<FDData>);
        });
        hideLoading()
    };
    fetchData().catch(console.error);
  },[loading]);

  return (
    <div className="page-container">
      <div className="page-bar">
        <Title level="H3">Описателна статистика</Title>
      </div>

      <div className="option-content">
        <Table
          columns={
            <>
              <TableColumn popinText="Упражнения" minWidth={600}>
                <Label>Упражнения</Label>
              </TableColumn>
              <TableColumn minWidth={600} popinText="Абсолютна честота">
                <Label>Абсолютна честота f</Label>
              </TableColumn>
              <TableColumn minWidth={600} popinText="Относителна честота">
                <Label>Относителна честота p (в %)</Label>
              </TableColumn>
            </>
          }
        >
          {data.map((row, index) => (
            <TableRow key={index}>
              <TableCell>
                <Label>{row.exercise}</Label>
              </TableCell>
              <TableCell>
                <Label>{row.absoluteFrequency.toFixed(2)}</Label>
              </TableCell>
              <TableCell>
                <Label>{row.relativeFrequency.toFixed(2)}</Label>
              </TableCell>
            </TableRow>
          ))}
        </Table>
      </div>
    </div>
  );
}

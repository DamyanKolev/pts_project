import {
  Button,
  Dialog,
  Bar,
  Title,
  Form,
  FormItem,
  Input,
  InputType,
  ProductSwitch,
  ProductSwitchItem,
} from "@ui5/webcomponents-react";
import React from "react";

interface Course {}

export default function Home() {
  const [dialogIsOpen, setDialogIsOpen] = React.useState(false);

  React.useEffect(() => {
    //   const dataPath = `${window.location.href}/data`;
    //   await fetch(dataPath)
    //     .then((res) => res.json)
    //     .then((json) => {
    //       setData(json as any as Array<Course>)
    //     });
    // setData(JSONdata as any as Array<Course>);
  });

  const onButtonClick = () => {
    setDialogIsOpen(true);
  };

  const handleClose = () => {
    setDialogIsOpen(false);
  };

  return (
    <div className="page-container">
      <div className="add-btn-container">
      <Button icon="add" design="Transparent" onClick={onButtonClick} />
        <Title level="H3"> Select Course</Title>
      </div>

      <div className="courses-container">
        <ProductSwitch>
          <ProductSwitchItem
            titleText="ПТС"
            subtitleText=""
            icon="course-book"
            targetSrc="/api/pts"
          />
          <ProductSwitchItem
            titleText="ПМУ"
            subtitleText=""
            icon="course-book"
            targetSrc="/api/pmu"
          />
        </ProductSwitch>
      </div>

      <Dialog
        open={dialogIsOpen}
        header={
          <Bar>
            <Title>Crete Course</Title>
          </Bar>
        }
        footer={
          <Bar endContent={<Button onClick={handleClose}>Close</Button>} />
        }
      >
        <Form>
          <FormItem label={"Sole Form Item"}>
            <Input type={InputType.Text} />
          </FormItem>
          <FormItem label={"Sole Form Item"}>
            <Input type={InputType.Text} />
          </FormItem>
          <FormItem label={"Sole Form Item"}>
            <Input type={InputType.Text} />
          </FormItem>
          <FormItem label={"Sole Form Item"}>
            <Input type={InputType.Text} />
          </FormItem>
          <FormItem label={"Sole Form Item"}>
            <Input type={InputType.Text} />
          </FormItem>
        </Form>
      </Dialog>
    </div>
  );
}
